package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JmiMemberUpgradeNote;
import com.joymain.ng.dao.JmiMemberUpgradeNoteDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiMemberUpgradeNoteDao")
public class JmiMemberUpgradeNoteDaoHibernate extends GenericDaoHibernate<JmiMemberUpgradeNote, Long> implements JmiMemberUpgradeNoteDao {

    public JmiMemberUpgradeNoteDaoHibernate() {
        super(JmiMemberUpgradeNote.class);
    }
}
