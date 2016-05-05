/**
 * 
 */

package org.cms.login;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.Model;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "Metaprofilo")
public class Metaprofilo extends Model {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;

	private String description;
	private String state;

	/**
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "metaprofiloxprofilo", joinColumns = @JoinColumn(name = "metaprofilo_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "profilo_id", referencedColumnName = "id"))
	private Collection<Profilo> profili;

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
	public Metaprofilo() {

		super();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return this.description;
	}

	/**
	 * @return the id
	 */
	public String getId() {

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
	 * @return the profili
	 */
	public Collection<Profilo> getProfili() {

		return this.profili;
	}

	/**
	 * @return the state
	 */
	public String getState() {

		return this.state;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {

		return this.user_id;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {

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
	 * @param profili
	 *            the profili to set
	 */
	public void setProfili(Collection<Profilo> profili) {

		this.profili = profili;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {

		this.state = state;
	}

	/**
	 * @param userId
	 *            the user_id to set
	 */
	public void setUser_id(String userId) {

		this.user_id = userId;
	}

}
