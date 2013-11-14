package edu.cmu.DBLPProcessor;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="coauthorship")
@XmlType(propOrder = { "coauthorshipid", "userid", "coauthorid", "count","datelist" })
public class Coauthorship {
	private static int c;
	private long coauthorshipid;
	private int userid;
	private int coauthorid;
	private int count;
	private List<String> date = new ArrayList<String>();
	private List<Publication> publicationList = new ArrayList<Publication>();
	
	public Coauthorship() {
		super();
		coauthorshipid = ++c;
	}

	@XmlElement(name="coauthorshipid")
	public long getCoauthorshipid() {
		return coauthorshipid;
	}

	@XmlElement(name="userid")
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}

	@XmlElement(name="coauthorid")
	public int getCoauthorid() {
		return coauthorid;
	}

	public void setCoauthorid(int coauthorid) {
		this.coauthorid = coauthorid;
	}

	@XmlElement(name="count")
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@XmlElementWrapper(name="datelist")
	@XmlElement(name="date")
	public List<String> getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date.add(date);
	}
	
	@XmlElement(name = "publicationlist")
	public List<Publication> getPublicationList() {
		return publicationList;
	}

	public void setPublicationList(List<Publication> publicationList) {
		this.publicationList = publicationList;
	}
	
	public void addPublicationToList(Publication publication) {
		this.publicationList.add(publication);
	}
	
}
