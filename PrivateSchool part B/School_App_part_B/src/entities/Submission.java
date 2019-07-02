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
public class Submission {

    private int subid;
    private LocalDate subdate;

    public Submission() {
    }

    public Submission(int subid, LocalDate subdate) {
        this.subid = subid;
        this.subdate = subdate;
    }

    public Submission(LocalDate subdate) {
        this.subdate = subdate;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public LocalDate getSubdate() {
        return subdate;
    }

    public void setSubdate(LocalDate subdate) {
        this.subdate = subdate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.subid;
        hash = 67 * hash + Objects.hashCode(this.subdate);
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
        final Submission other = (Submission) obj;
        if (this.subid != other.subid) {
            return false;
        }
        if (!Objects.equals(this.subdate, other.subdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "submission_id: " + subid + ", submission_date: " + subdate + '}';
    }

}
