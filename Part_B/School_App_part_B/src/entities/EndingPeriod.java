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
public class EndingPeriod {

    private int epid;
    private LocalDate epdate;

    public EndingPeriod() {
    }

    public EndingPeriod(int epid, LocalDate epdate) {
        this.epid = epid;
        this.epdate = epdate;
    }

    public EndingPeriod(LocalDate epdate) {
        this.epdate = epdate;
    }

    public int getEpid() {
        return epid;
    }

    public void setEpid(int epid) {
        this.epid = epid;
    }

    public LocalDate getEpdate() {
        return epdate;
    }

    public void setEpdate(LocalDate epdate) {
        this.epdate = epdate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.epid;
        hash = 17 * hash + Objects.hashCode(this.epdate);
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
        final EndingPeriod other = (EndingPeriod) obj;
        if (this.epid != other.epid) {
            return false;
        }
        if (!Objects.equals(this.epdate, other.epdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ending_Id=" + epid + ", Ending_date=" + epdate + '}';
    }

}
