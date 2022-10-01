package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

//@Entity
public class Hawjin {

    @Id
    Integer hno;

    String data;

    public Hawjin(){}

    public Hawjin(Integer hno, String data) {
        this.hno = hno;
        this.data = data;
    }

    public Integer getHno() {
        return hno;
    }

    public void setHno(Integer hno) {
        this.hno = hno;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
