import com.cucu.report.configuration.CucuConfiguration;
import com.cucu.report.exceptions.file.DirectoryNotPresentException;
import com.cucu.report.exceptions.file.FileNotPresentException;
import com.cucu.report.file.FileIo;
import com.cucu.report.pojo.EnvelopeData;
import com.cucu.report.transformer.NdJsonToEnvelopeDataTransformer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * This is the main class that will perform actions in the below format
 * 1. Get all the ndjson files from target directory - Done
 * 2. Create the directory for report and copy css and js files - Pending to be done in end
 * 3. Transform all the ndjson file to EnvelopData - Done
 * 4. Transform all the EnvelopeData to pojo structure - Pending
 * 5. Perform analysis i.e. like total time taken feature and annotation wise - Pending
 */
public class CucuReport {

    public static void main(String[] args) throws FileNotPresentException, DirectoryNotPresentException {
        ApplicationContext context = new AnnotationConfigApplicationContext(CucuConfiguration.class);

        //1. Get all the ndjson files from target directory
        Set<File> files = context.getBean(FileIo.class).getFiles();

        //2. Create the directory for report and copy css and js files - Pending to be done in end

        //3. Transform all the ndjson file to EnvelopData
        List<EnvelopeData> envelopeDataList = context.getBean(NdJsonToEnvelopeDataTransformer.class)
                .transformAllNdJsonToEnvelopeData(files);

        //4. Transform all the EnvelopeData to pojo structure


    }

}
