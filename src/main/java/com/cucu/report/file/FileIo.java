package com.cucu.report.file;

import com.cucu.report.annotations.Log;
import com.cucu.report.exceptions.file.DirectoryNotPresentException;
import com.cucu.report.exceptions.file.FileNotPresentException;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import io.cucumber.messages.NdjsonToMessageIterable;
import io.cucumber.messages.types.Envelope;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FileIo {

    @Log
    private Logger logger;

    @Value("${file.location}")
    String fileLocation;

    @Value("${file.extension}")
    String fileExtension;

    /**
     * Get ndjson files from the given location
     *
     * @return
     * @throws DirectoryNotPresentException
     * @throws FileNotPresentException
     */
    public Set<File> getFiles() throws DirectoryNotPresentException, FileNotPresentException {
        Set<File> fileSet;
        File fileDirectory = new File(Path.of(System.getProperty("user.dir"), fileLocation).toString());
        if (fileDirectory.exists()) {
            fileSet = Arrays.stream(Objects.requireNonNull(fileDirectory.listFiles()))
                    .filter(fileDirectoryElements -> !fileDirectoryElements.isDirectory())
                    .filter(fileDirectoryFileElements -> fileDirectoryFileElements.getName().endsWith(fileExtension))
                    .collect(Collectors.toSet());
            if (fileSet.size() > 0) {
                logger.info(String.format("Below file found in directory %s: %s",
                        Path.of(System.getProperty("user.dir"), fileLocation)
                        ,fileSet.stream().map(File::getName).collect(Collectors.toSet())));
            } else {
                throw new FileNotPresentException(fileExtension);
            }
        } else {
            throw new DirectoryNotPresentException(fileLocation);
        }
        return fileSet;
    }

    /**
     * Read Cucumbers ndjson file
     *
     * @param input
     * @return
     */
    public Iterable<Envelope> readNdJsonFile(InputStream input) {
        return new NdjsonToMessageIterable(input, (json) -> JsonMapper.builder()
                .addModule(new Jdk8Module())
                .addModule(new ParameterNamesModule(Mode.PROPERTIES))
                .serializationInclusion(Include.NON_ABSENT)
                .constructorDetector(ConstructorDetector.USE_PROPERTIES_BASED)
                .enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING)
                .enable(DeserializationFeature.USE_LONG_FOR_INTS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(JsonGenerator.Feature.AUTO_CLOSE_TARGET)
                .build().readValue(json, Envelope.class));
    }

}
