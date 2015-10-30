package bcel.cardcenter.cbs.aarofat.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedNativeQueries({ 
	@NamedNativeQuery(
			name = "GETATMBYIDNAME", 
			query = "? = CALL CBSPACKAGE.GETATMBYIDNAME(:atmId,:atmName)", 
			resultClass = User.class)
})

@NamedQueries({
	@NamedQuery(
			name="getAtmId",
			query="from Atm atm where atm.atmId like :atmId and atm.atmName like :atmName order by atm.atmId"
	),
	@NamedQuery(
			name = "getAtmTotal", 
			query = "from Atm"
	),
	@NamedQuery(
			name="getAtmByPage",
			query="from Atm atm"
	)
})
@Entity
@Table(name="ATM_INFO")
public class Atm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name="ATM_ID")
	private String atmId;
	@Column(name="BRANCH_NAME")
	private String atmName;
	@Column(name="IP")
	private String atmIp;
	@Column(name="ATM_LOCATE")
	private String atmLocation;
	
	public String getAtmId() {
		return atmId;
	}
	public void setAtmId(String atmId) {
		this.atmId = atmId;
	}
	public String getAtmName() {
		return atmName;
	}
	public void setAtmName(String atmName) {
		this.atmName = atmName;
	}
	public String getAtmIp() {
		return atmIp;
	}
	public void setAtmIp(String atmIp) {
		this.atmIp = atmIp;
	}
	public String getAtmLocation() {
		return atmLocation;
	}
	public void setAtmLocation(String atmLocation) {
		this.atmLocation = atmLocation;
	}
	@Override
	public String toString() {
		return "Atm [atmId=" + atmId + ", atmName=" + atmName + ", atmIp="
				+ atmIp + ", atmLocation=" + atmLocation + "]";
	}
	
}
