package il.ijse.gdse66.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class customer implements Serializable {
    private String id;
    private String name;
    private String address;

}
