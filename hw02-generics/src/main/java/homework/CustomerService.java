package homework;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    TreeMap<Customer, String> tree = new TreeMap<>(
            new Comparator<Customer>() {
                @Override
                public int compare(Customer customer, Customer t1) {
                    return customer.compareTo(t1);
                }
            }
    );

    public Map.Entry<Customer, String> getSmallest() {
        TreeMap<Customer, String> temp = new TreeMap<>();
        Map.Entry<Customer, String> smallest = tree.firstEntry();
        Customer customer = smallest.getKey();

        Customer newCustomer = new Customer(
                customer.getId(),
                customer.getName(),
                customer.getScores()
        );

        temp.put(newCustomer, smallest.getValue());
        return temp.firstEntry();
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var keyExist = tree.containsKey(customer);
        var nextValue = tree.higherEntry(customer);

        if (keyExist && nextValue == null) {
            return tree.lastEntry();
        }

        return nextValue;
    }

    public void add(Customer customer, String data) {
        this.tree.put(customer, data);
    }
}
