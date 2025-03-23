package com.cucu.report.transformer;

import com.cucu.report.annotations.Log;
import com.cucu.report.exceptions.UnmatchedCaseException;
import com.cucu.report.exceptions.file.DirectoryNotPresentException;
import com.cucu.report.exceptions.file.FileNotPresentException;
import com.cucu.report.file.FileIo;
import com.cucu.report.pojo.EnvelopeData;
import io.cucumber.messages.types.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NdJsonToEnvelopeDataTransformer {

    @Log
    private Logger logger;

    @Autowired
    FileIo fileIo;

    public List<EnvelopeData> transformAllNdJsonToEnvelopeData(Set<File> files) {
        logger.info("Started transforming files");
        return files.stream()
                .map(file -> {
                    try {
                        return transformNdJsonToEnvelopeData(file);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public EnvelopeData transformNdJsonToEnvelopeData(File file) throws FileNotFoundException {
        EnvelopeData envelopeData = getEnvelopeData();
        fileIo.readNdJsonFile(new FileInputStream(file)).forEach(envelope -> {
            for (Field field : envelope.getClass().getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    String name = field.getName();
                    Object value = field.get(envelope);
                    if (value != null) {
                        logger.info(name + " = " + value);
                        switch (name) {
                            case "attachment":
                                envelopeData.addAttachment((Attachment) value);
                                break;
                            case "gherkinDocument":
                                envelopeData.addGherkinDocument((GherkinDocument) value);
                                break;
                            case "hook":
                                envelopeData.addHook((Hook) value);
                                break;
                            case "meta":
                                envelopeData.addMeta((Meta) value);
                                break;
                            case "parameterType":
                                envelopeData.addParameterType((ParameterType) value);
                                break;
                            case "parseError":
                                envelopeData.addParseError((ParseError) value);
                                break;
                            case "pickle":
                                envelopeData.addPickle((Pickle) value);
                                break;
                            case "source":
                                envelopeData.addSource((Source) value);
                                break;
                            case "stepDefinition":
                                envelopeData.addStepDefinition((StepDefinition) value);
                                break;
                            case "testCase":
                                envelopeData.addTestCase((TestCase) value);
                                break;
                            case "testCaseFinished":
                                envelopeData.addTestCaseFinished((TestCaseFinished) value);
                                break;
                            case "testCaseStarted":
                                envelopeData.addTestCaseStarted((TestCaseStarted) value);
                                break;
                            case "testRunFinished":
                                envelopeData.addTestRunFinished((TestRunFinished) value);
                                break;
                            case "testRunStarted":
                                envelopeData.addTestRunStarted((TestRunStarted) value);
                                break;
                            case "testStepFinished":
                                envelopeData.addTestStepFinished((TestStepFinished) value);
                                break;
                            case "testStepStarted":
                                envelopeData.addTestStepStarted((TestStepStarted) value);
                                break;
                            case "undefinedParameterType":
                                envelopeData.addUndefinedParameterType((UndefinedParameterType) value);
                                break;
                            default:
                                throw new UnmatchedCaseException(String.format("%s is invalid/unimplemented envelope type case", name));
                        }
                    }
                } catch (IllegalAccessException | UnmatchedCaseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return envelopeData;
    }

    @Lookup
    public EnvelopeData getEnvelopeData() {
        return null;
    }


}
