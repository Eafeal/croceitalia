package org.cms.login;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.Model;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "Profilo")
public class Profilo extends Model {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private int id;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	private Timestamp insertTime;

	@Temporal(TemporalType.DATE)
	private Timestamp lastUpdateTime;

	private String user_id;

	/**
	 * 
	 */
	public Profilo() {
		super();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @return the insertTime
	 */
	public Timestamp getInsertTime() {
		return this.insertTime;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Timestamp getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return this.user_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param insertTime
	 *            the insertTime to set
	 */
	public void setInsertTime(Timestamp insertTime) {
		this.insertTime = insertTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @param userId
	 *            the user_id to set
	 */
	public void setUser_id(String userId) {
		this.user_id = userId;
	}

}
