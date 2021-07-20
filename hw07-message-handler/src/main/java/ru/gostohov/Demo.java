package ru.gostohov;

import ru.gostohov.handler.ComplexProcessor;
import ru.gostohov.listener.ListenerPrinter;
import ru.gostohov.processor.LoggerProcessor;
import ru.gostohov.processor.ProcessorConcatFields;
import ru.gostohov.processor.ProcessorUpperField10;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        var processors = List.of(new ProcessorConcatFields(),
                new LoggerProcessor(new ProcessorUpperField10()));

        var complexProcessor = new ComplexProcessor(processors, (ex) -> {});
        var listenerPrinter = new ListenerPrinter();
        complexProcessor.addListener(listenerPrinter);

        var message = new Message.Builder()
                .field1("field1")
                .field2("field2")
                .field3("field3")
                .field6("field6")
                .field10("field10")
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
