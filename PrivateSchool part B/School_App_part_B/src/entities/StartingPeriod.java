/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Objects;

public class StartingPeriod {

    private int spid;
    private LocalDate spdate;

    public StartingPeriod() {
    }

    public StartingPeriod(int spid, LocalDate spdate) {
        this.spid = spid;
        this.spdate = spdate;
    }

    public StartingPeriod(LocalDate spdate) {
        this.spdate = spdate;
    }

    public int getSpid() {
        return spid;
    }

    public void setSpid(int spid) {
        this.spid = spid;
    }

    public LocalDate getSpdate() {
        return spdate;
    }

    public void setSpdate(LocalDate spdate) {
        this.spdate = spdate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.spid;
        hash = 13 * hash + Objects.hashCode(this.spdate);
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
        final StartingPeriod other = (StartingPeriod) obj;
        if (this.spid != other.spid) {
            return false;
        }
        if (!Objects.equals(this.spdate, other.spdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Starting_Id: " + spid + ", Starting_date: " + spdate + '}';
    }

}
