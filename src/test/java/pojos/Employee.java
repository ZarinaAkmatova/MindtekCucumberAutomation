package pojos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Employee {

   private String firstName;
   private String lastName;
   private Integer employeeId;
   private Department department;
   private Job job;

}
