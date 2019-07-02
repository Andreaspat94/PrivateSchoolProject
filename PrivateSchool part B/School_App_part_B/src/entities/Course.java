/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author George
 */
public class Course {

    private int ccode;
    private String ctitle;
    private String cstream;
    private String ctype;
    private LocalDate cstart;
    private LocalDate cend;

    public Course() {
    }

    public Course(String ctitle, String cstream, String ctype, LocalDate cstart, LocalDate cend) {
        this.ctitle = ctitle;
        this.cstream = cstream;
        this.ctype = ctype;
        this.cstart = cstart;
        this.cend = cend;
    }

    public Course(int ccode, String ctitle, String cstream, String ctype, LocalDate cstart, LocalDate cend) {
        this.ccode = ccode;
        this.ctitle = ctitle;
        this.ctype = ctype;
        this.cstart = cstart;
        this.cend = cend;
        this.cstream = cstream;
    }

    public int getCcode() {
        return ccode;
    }

    public void setCcode(int ccode) {
        this.ccode = ccode;
    }

    public String getCtitle() {
        return ctitle;
    }

    public void setCtitle(String ctitle) {
        this.ctitle = ctitle;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public LocalDate getCstart() {
        return cstart;
    }

    public void setCstart(LocalDate cstart) {
        this.cstart = cstart;
    }

    public LocalDate getCend() {
        return cend;
    }

    public void setCend(LocalDate cend) {
        this.cend = cend;
    }

    public String getCstream() {
        return cstream;
    }

    public void setCstream(String cstream) {
        this.cstream = cstream;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.ccode;
        hash = 89 * hash + Objects.hashCode(this.ctitle);
        hash = 89 * hash + Objects.hashCode(this.cstream);
        hash = 89 * hash + Objects.hashCode(this.ctype);
        hash = 89 * hash + Objects.hashCode(this.cstart);
        hash = 89 * hash + Objects.hashCode(this.cend);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.ccode != other.ccode) {
            return false;
        }
        if (!Objects.equals(this.ctitle, other.ctitle)) {
            return false;
        }
        if (!Objects.equals(this.cstream, other.cstream)) {
            return false;
        }
        if (!Objects.equals(this.ctype, other.ctype)) {
            return false;
        }
        if (!Objects.equals(this.cstart, other.cstart)) {
            return false;
        }
        if (!Objects.equals(this.cend, other.cend)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Course_Id: " + ccode + ", Title: " + ctitle + ", Stream: " + cstream + ", Type: " + ctype + ", Starting_date: " + cstart + ", Ending_date: " + cend + '}';
    }

}
