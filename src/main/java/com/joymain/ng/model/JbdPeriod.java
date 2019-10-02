package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JBD_PERIOD")

@XmlRootElement
public class JbdPeriod extends BaseObject implements RowMapper<JbdPeriod>,Serializable {
   
	private static final long serialVersionUID = -6595971690096194488L;
	
	private Long id;
    private Integer wyear;
    private Integer wmonth;
    private Integer wweek;
    private Date startTime;
    private Date endTime;
    private Integer lastMark;
    private Integer bonusSend;
    private Integer archivingStatus;
    private Integer monthStatus;
    private Long oldWeek;
    private Long dayStatus;
    private Long inType;
    private Integer fyear;
    private Integer fmonth;
    private Integer fweek;
    private Integer aweek;

    @Id      
    @Column(name="ID", unique=true, nullable=false, precision=12, scale=0)    
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
   
    @Column(name="START_TIME", length=7)
    public Date getStartTime() {
        return this.startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @Column(name="END_TIME", length=7)
    public Date getEndTime() {
        return this.endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    @Column(name="LAST_MARK", precision=2, scale=0)
    public Integer getLastMark() {
        return this.lastMark;
    }
    
    public void setLastMark(Integer lastMark) {
        this.lastMark = lastMark;
    }
    
    @Column(name="BONUS_SEND", precision=2, scale=0)
    public Integer getBonusSend() {
        return this.bonusSend;
    }
    
    public void setBonusSend(Integer bonusSend) {
        this.bonusSend = bonusSend;
    }
    
    @Column(name="ARCHIVING_STATUS", precision=2, scale=0)
    public Integer getArchivingStatus() {
        return this.archivingStatus;
    }
    
    public void setArchivingStatus(Integer archivingStatus) {
        this.archivingStatus = archivingStatus;
    }
    
    @Column(name="MONTH_STATUS", precision=2, scale=0)
    public Integer getMonthStatus() {
        return this.monthStatus;
    }
    
    public void setMonthStatus(Integer monthStatus) {
        this.monthStatus = monthStatus;
    }
    
    @Column(name="OLD_WEEK", precision=10, scale=0)
    public Long getOldWeek() {
        return this.oldWeek;
    }
    
    public void setOldWeek(Long oldWeek) {
        this.oldWeek = oldWeek;
    }
    
    @Column(name="DAY_STATUS", precision=2, scale=0)
    public Long getDayStatus() {
        return this.dayStatus;
    }
    
    public void setDayStatus(Long dayStatus) {
        this.dayStatus = dayStatus;
    }
    
    @Column(name="IN_TYPE", precision=22, scale=0)
    public Long getInType() {
        return this.inType;
    }
    
    public void setInType(Long inType) {
        this.inType = inType;
    }

    @Column(name="A_WEEK", precision=8, scale=0)
    public Integer getAweek() {
		return aweek;
	}

	public void setAweek(Integer aweek) {
		this.aweek = aweek;
	}

    @Column(name="F_MONTH", precision=8, scale=0)
	public Integer getFmonth() {
		return fmonth;
	}

	public void setFmonth(Integer fmonth) {
		this.fmonth = fmonth;
	}

    @Column(name="F_WEEK", precision=8, scale=0)
	public Integer getFweek() {
		return fweek;
	}

	public void setFweek(Integer fweek) {
		this.fweek = fweek;
	}

	@Column(name="F_YEAR", precision=8, scale=0)
	public Integer getFyear() {
		return fyear;
	}

	public void setFyear(Integer fyear) {
		this.fyear = fyear;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JbdPeriod pojo = (JbdPeriod) o;

        if (wyear != null ? !wyear.equals(pojo.wyear) : pojo.wyear != null) return false;
        if (wmonth != null ? !wmonth.equals(pojo.wmonth) : pojo.wmonth != null) return false;
        if (wweek != null ? !wweek.equals(pojo.wweek) : pojo.wweek != null) return false;
        if (startTime != null ? !startTime.equals(pojo.startTime) : pojo.startTime != null) return false;
        if (endTime != null ? !endTime.equals(pojo.endTime) : pojo.endTime != null) return false;
        if (lastMark != null ? !lastMark.equals(pojo.lastMark) : pojo.lastMark != null) return false;
        if (bonusSend != null ? !bonusSend.equals(pojo.bonusSend) : pojo.bonusSend != null) return false;
        if (archivingStatus != null ? !archivingStatus.equals(pojo.archivingStatus) : pojo.archivingStatus != null) return false;
        if (monthStatus != null ? !monthStatus.equals(pojo.monthStatus) : pojo.monthStatus != null) return false;
        if (oldWeek != null ? !oldWeek.equals(pojo.oldWeek) : pojo.oldWeek != null) return false;
        if (dayStatus != null ? !dayStatus.equals(pojo.dayStatus) : pojo.dayStatus != null) return false;
        if (inType != null ? !inType.equals(pojo.inType) : pojo.inType != null) return false;
        if (fyear != null ? !fyear.equals(pojo.fyear) : pojo.fyear != null) return false;
        if (fmonth != null ? !fmonth.equals(pojo.fmonth) : pojo.fmonth != null) return false;
        if (fweek != null ? !fweek.equals(pojo.fweek) : pojo.fweek != null) return false;
        if (aweek != null ? !aweek.equals(pojo.aweek) : pojo.aweek != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (wyear != null ? wyear.hashCode() : 0);
        result = 31 * result + (wmonth != null ? wmonth.hashCode() : 0);
        result = 31 * result + (wweek != null ? wweek.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (lastMark != null ? lastMark.hashCode() : 0);
        result = 31 * result + (bonusSend != null ? bonusSend.hashCode() : 0);
        result = 31 * result + (archivingStatus != null ? archivingStatus.hashCode() : 0);
        result = 31 * result + (monthStatus != null ? monthStatus.hashCode() : 0);
        result = 31 * result + (oldWeek != null ? oldWeek.hashCode() : 0);
        result = 31 * result + (dayStatus != null ? dayStatus.hashCode() : 0);
        result = 31 * result + (inType != null ? inType.hashCode() : 0);
        result = 31 * result + (fyear != null ? fyear.hashCode() : 0);
        result = 31 * result + (fmonth != null ? fmonth.hashCode() : 0);
        result = 31 * result + (fweek != null ? fweek.hashCode() : 0);
        result = 31 * result + (aweek != null ? aweek.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("wyear").append("='").append(getWyear()).append("', ");
        sb.append("wmonth").append("='").append(getWmonth()).append("', ");
        sb.append("wweek").append("='").append(getWweek()).append("', ");
        sb.append("startTime").append("='").append(getStartTime()).append("', ");
        sb.append("endTime").append("='").append(getEndTime()).append("', ");
        sb.append("lastMark").append("='").append(getLastMark()).append("', ");
        sb.append("bonusSend").append("='").append(getBonusSend()).append("', ");
        sb.append("archivingStatus").append("='").append(getArchivingStatus()).append("', ");
        sb.append("monthStatus").append("='").append(getMonthStatus()).append("', ");
        sb.append("oldWeek").append("='").append(getOldWeek()).append("', ");
        sb.append("dayStatus").append("='").append(getDayStatus()).append("', ");
        sb.append("inType").append("='").append(getInType()).append("', ");
        sb.append("fyear").append("='").append(getFyear()).append("', ");
        sb.append("fmonth").append("='").append(getFmonth()).append("', ");
        sb.append("fweek").append("='").append(getFweek()).append("', ");
        sb.append("aweek").append("='").append(getAweek()).append("'");
        sb.append("]");
      
        return sb.toString();
    }


    @Column(name="W_MONTH", precision=8, scale=0)
	public Integer getWmonth() {
		return wmonth;
	}

	public void setWmonth(Integer wmonth) {
		this.wmonth = wmonth;
	}
	
    @Column(name="W_WEEK", precision=8, scale=0)
	public Integer getWweek() {
		return wweek;
	}

	public void setWweek(Integer wweek) {
		this.wweek = wweek;
	}
	
    @Column(name="W_YEAR", precision=8, scale=0)
	public Integer getWyear() {
		return wyear;
	}

	public void setWyear(Integer wyear) {
		this.wyear = wyear;
	}

	@Override
	public JbdPeriod mapRow(ResultSet rs, int arg1) throws SQLException {
		JbdPeriod per = new JbdPeriod();
		
		per.setId(rs.getLong("id"));
		per.setWmonth(rs.getInt("w_month"));
		per.setWyear(rs.getInt("w_year"));
		per.setWweek(rs.getInt("w_week"));
		per.setStartTime(rs.getDate("start_Time"));
		per.setEndTime(rs.getDate("end_Time"));
		per.setLastMark(rs.getInt("last_Mark"));
		per.setBonusSend(rs.getInt("bonus_Send"));
		per.setArchivingStatus(rs.getInt("archiving_Status"));
		per.setMonthStatus(rs.getInt("month_Status"));
		per.setOldWeek(rs.getLong("old_Week"));
		per.setDayStatus(rs.getLong("day_Status"));
		per.setInType(rs.getLong("in_Type"));
		per.setFyear(rs.getInt("f_year"));
		per.setFmonth(rs.getInt("f_month"));
		per.setFweek(rs.getInt("f_week"));
		per.setAweek(rs.getInt("a_week"));
		
		return per;
	}

}
