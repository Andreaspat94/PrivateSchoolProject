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
public class Assignment {

    private int aid;
    private String atitle;
    private String adescription;
    private LocalDate asubtime;
    private int aoralMark;
    private int atotalMark;

    public Assignment() {
    }

    public Assignment(String atitle, String adescription, LocalDate asubtime, int aoralMark, int atotalMark) {
        this.atitle = atitle;
        this.adescription = adescription;
        this.asubtime = asubtime;
        this.aoralMark = aoralMark;
        this.atotalMark = atotalMark;
    }

    public Assignment(int aid, String atitle, String adescription, LocalDate asubtime, int aoralMark, int atotalMark) {
        this.aid = aid;
        this.atitle = atitle;
        this.adescription = adescription;
        this.asubtime = asubtime;
        this.aoralMark = aoralMark;
        this.atotalMark = atotalMark;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAtitle() {
        return atitle;
    }

    public void setAtitle(String atitle) {
        this.atitle = atitle;
    }

    public String getAdescription() {
        return adescription;
    }

    public void setAdescription(String adescription) {
        this.adescription = adescription;
    }

    public LocalDate getAsubtime() {
        return asubtime;
    }

    public void setAsubtime(LocalDate asubtime) {
        this.asubtime = asubtime;
    }

    public int getAoralMark() {
        return aoralMark;
    }

    public void setAoralMark(int aoralMark) {
        this.aoralMark = aoralMark;
    }

    public int getAtotalMark() {
        return atotalMark;
    }

    public void setAtotalMark(int atotalMark) {
        this.atotalMark = atotalMark;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + this.aid;
        hash = 23 * hash + Objects.hashCode(this.atitle);
        hash = 23 * hash + Objects.hashCode(this.adescription);
        hash = 23 * hash + Objects.hashCode(this.asubtime);
        hash = 23 * hash + this.aoralMark;
        hash = 23 * hash + this.atotalMark;
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
        final Assignment other = (Assignment) obj;
        if (this.aid != other.aid) {
            return false;
        }
        if (this.aoralMark != other.aoralMark) {
            return false;
        }
        if (this.atotalMark != other.atotalMark) {
            return false;
        }
        if (!Objects.equals(this.atitle, other.atitle)) {
            return false;
        }
        if (!Objects.equals(this.adescription, other.adescription)) {
            return false;
        }
        if (!Objects.equals(this.asubtime, other.asubtime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Assignment_Id: " + aid + ", Title: " + atitle + ", Description: " + adescription + ", Submission_date: " + asubtime + ", OralMark: " + aoralMark + ", TotalMark=" + atotalMark + '}';
    }

}
