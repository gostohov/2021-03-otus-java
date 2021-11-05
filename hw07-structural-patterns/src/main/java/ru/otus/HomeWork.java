package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.listener.homework.HistoryListener;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;
import ru.otus.processor.homework.EvenSecondProcessor;
import ru.otus.processor.homework.SwapF11F12Processor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeWork {

    /*
     Реализовать to do:
       1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
       2. Сделать процессор, который поменяет местами значения field11 и field12
       3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
       4. Сделать Listener для ведения истории: старое сообщение - новое (подумайте, как сделать, чтобы сообщения не портились)
     */

    public static void main(String[] args) {
        /*
           по аналогии с Demo.class
           из элеменов "to do" создать new ComplexProcessor и обработать сообщение
         */
        var processors = List.of(
            new SwapF11F12Processor(),
            new EvenSecondProcessor(LocalDateTime::now)
        );

        var complexProcessor = new ComplexProcessor(processors, (ex) -> System.out.println(ex.getMessage()));
        MessageChanger messageChanger = new MessageChanger(complexProcessor);

        var historyListener = new HistoryListener();
        complexProcessor.addListener(historyListener);

        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"ofm01", "ofm02", "ofm03");
        ObjectForMessage ofm = new ObjectForMessage();
        ofm.setData(list);

        // Создаем сообщение и запускаем его обработку
        var message = new Message.Builder(171L)
                .field11("F_11")
                .field12("F_12")
                .field13(ofm)
                .build();

        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        // Изменяем сообщение и снова обрабатываем на том же complexProcessor'е
        list = new ArrayList<>();
        Collections.addAll(list,"ofm_101", "ofm_102", "ofm_103");
        messageChanger.mutateMessage(message, list);

        list = new ArrayList<>();
        Collections.addAll(list,"ofm_104", "ofm_105", "ofm_106");
        messageChanger.mutateMessage(message, list);

        list = new ArrayList<>();
        Collections.addAll(list,"ofm_107", "ofm_108", "ofm_109");
        messageChanger.mutateMessage(message, list);

        complexProcessor.removeListener(historyListener);

        // Печатаем историю изменений. Новый ObjectForMessage не влияет на первые Message в listenerHistory
        System.out.println("\nHistory of changes:");
        historyListener.printHistory();
    }

    public static class MessageChanger {

        private final ComplexProcessor complexProcessor;

        public MessageChanger(ComplexProcessor complexProcessor) { this.complexProcessor = complexProcessor; }

        public void mutateMessage(Message message, List<String> list) {
            ObjectForMessage ofm = new ObjectForMessage();
            ofm.setData(list);
            message = message.toBuilder().field13(ofm).build();
            Message result = complexProcessor.handle(message);
            System.out.println("result:" + result);
        }
    }
}
