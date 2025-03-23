package com.cucu.report.pojo;

import io.cucumber.messages.types.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(value="prototype")
public class EnvelopeData {

    private List<Attachment> attachmentList;
    private List<GherkinDocument> gherkinDocumentList;
    private List<Hook> hookList;
    private List<Meta> metaList;
    private List<ParameterType> parameterTypeList;
    private List<ParseError> parseErrorList;
    private List<Pickle> pickleList;
    private List<Source> sourceList;
    private List<StepDefinition> stepDefinitionList;
    private List<TestCase> testCaseList;
    private List<TestCaseFinished> testCaseFinishedList;
    private List<TestCaseStarted> testCaseStartedList;
    private List<TestRunFinished> testRunFinished;
    private List<TestRunStarted> testRunStartedList;
    private List<TestStepFinished> testStepFinishedList;
    private List<TestStepStarted> testStepStartedList;
    private List<UndefinedParameterType> undefinedParameterTypeList;

    public void addAttachment(Attachment attachment) {
        this.attachmentList.add(attachment);
    }

    public void addGherkinDocument(GherkinDocument gherkinDocument) {
        this.gherkinDocumentList.add(gherkinDocument);
    }

    public void addHook(Hook hook) {
        this.hookList.add(hook);
    }

    public void addMeta(Meta meta) {
        this.metaList.add(meta);
    }

    public void addParameterType(ParameterType parameterType) {
        this.parameterTypeList.add(parameterType);
    }

    public void addParseError(ParseError parseError) {
        this.parseErrorList.add(parseError);
    }

    public void addPickle(Pickle pickle) {
        this.pickleList.add(pickle);
    }

    public void addSource(Source source) {
        this.sourceList.add(source);
    }

    public void addStepDefinition(StepDefinition stepDefinition) {
        this.stepDefinitionList.add(stepDefinition);
    }

    public void addTestCase(TestCase testCase) {
        this.testCaseList.add(testCase);
    }

    public void addTestCaseFinished(TestCaseFinished testCaseFinished) {
        this.testCaseFinishedList.add(testCaseFinished);
    }

    public void addTestCaseStarted(TestCaseStarted testCaseStarted) {
        this.testCaseStartedList.add(testCaseStarted);
    }

    public void addTestRunFinished(TestRunFinished testRunFinished) {
        this.testRunFinished.add(testRunFinished);
    }

    public void addTestRunStarted(TestRunStarted testRunStarted) {
        this.testRunStartedList.add(testRunStarted);
    }

    public void addTestStepFinished(TestStepFinished testStepFinished) {
        this.testStepFinishedList.add(testStepFinished);
    }

    public void addTestStepStarted(TestStepStarted testStepStarted) {
        this.testStepStartedList.add(testStepStarted);
    }

    public void addUndefinedParameterType(UndefinedParameterType undefinedParameterType) {
        this.undefinedParameterTypeList.add(undefinedParameterType);
    }

}
