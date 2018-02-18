package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Customer {


	private int id;
	
    private String fullname;

    private String pass;
    
	private long afm;
	
	private long telephone;
	
	public Customer() {
    }
    
    public Customer( String fullname,String pass, long afm, long telephone) {
		
		
		this.fullname = fullname;
		this.pass=pass;
		this.afm = afm;
		this.telephone = telephone;
	}

	public int getId() {
            return id;
    }

    public void setId(int id) {
            this.id = id;
    }

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}
	
	public long getTelephone() {
		return telephone;
	}


	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", fullname=" + fullname + ", pass=" + pass + ", afm=" + afm + ", telephone="
				+ telephone + "]";
	}
//	if object mapper fail, we have this.
	public String toJSON() {
		return "{\"fullname\":\""+fullname+"\",\"pass\":\""+pass+"\",\"afm\":"+afm+",\"telephone\":"+telephone+"}";
	}

}
