package validation.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Table(name="users")
@Entity
public class UserEntity {
	
	@Id
	@GeneratedValue
	private int id;
	//@NotNull(message="name cannot be null")
	@NotEmpty(message="name must not be empty")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String name;
	@Digits(message="Age should be an appropriate number", fraction = 0, integer = 3)
	@Min(value=1 , message="Invalid Age")
	@Max(value=99 , message="Invalid Age")
	private int age;

	public UserEntity(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public UserEntity() {
		super();
	}
	public String getName() { 
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

