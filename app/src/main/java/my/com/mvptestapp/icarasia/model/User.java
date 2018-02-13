package my.com.mvptestapp.icarasia.model;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.UserRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by fadzlirazali on 04/12/2016.
 */

@Parcel(implementations = { UserRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { User.class })
public class User extends RealmObject {

    @PrimaryKey
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private int userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

}
