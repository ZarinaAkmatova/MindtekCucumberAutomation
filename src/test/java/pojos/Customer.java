package pojos;

import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;

@Getter
@Setter
public class Customer implements Comparator<Customer> {

    private String accountOpenDate;
    private Boolean active;
    private String address;
    private String fullName;
    private Boolean isActive;
    private Integer id;

    @Override
    public int compare(Customer c1, Customer c2){
        return c1.fullName.compareTo(c2.fullName);

    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
