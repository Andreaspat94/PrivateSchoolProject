/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author George
 */
public class Headline {

    private int hid;
    private String hname;

    public Headline() {
    }

    public Headline(String hname) {
        this.hname = hname;
    }

    public Headline(int hid, String hname) {
        this.hid = hid;
        this.hname = hname;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.hid;
        hash = 97 * hash + Objects.hashCode(this.hname);
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
        final Headline other = (Headline) obj;
        if (this.hid != other.hid) {
            return false;
        }
        if (!Objects.equals(this.hname, other.hname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "headline_id: " + hid + ", headline_name: " + hname + '}';
    }

}
