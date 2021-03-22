package com.mzr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mzr.dto.EmployeeDTO;
import com.mzr.model.DatabaseSequence;
import com.mzr.model.Employee;
import com.mzr.model.Project;
import com.mzr.repo.EmployeeRepo;

@Service("empService")
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo repo;
	@Autowired
	private MongoOperations mongo;

	@Override
	public String insert(EmployeeDTO dto, String msg) {

		Employee e = new Employee();
		List<Project> projList = new ArrayList<>();
		dto.getProject().forEach(p -> {
			Project pro = new Project();
			pro.setProjno(Integer.valueOf(p.getProjno()));
			pro.setProjName(p.getProjName());
			pro.setDatabase(p.getDatabase());
			pro.setLang(p.getLang());
			pro.setTeamSize(Integer.valueOf(p.getTeamSize()));
			projList.add(pro);
		});

		BeanUtils.copyProperties(dto, e);
		e.setProject(projList);
		String mg = null;
		if(msg.equalsIgnoreCase("insert")) {
			mg = (repo.save(e)) != null ? "Employee saved.\nWhose ID is ::" + e.getId() : "Employee not saved";
		}
		if(msg.equalsIgnoreCase("update")) {
			mg =  (repo.save(e)) != null ? "Employee updated.\nWhose ID is ::" + e.getId() : "Employee not updated";
		}	
		return mg;
	}

	@Override
	public List<Employee> getAll() {
		
		return repo.findAll();
	}

	@Override
	public long generateSequence(String seqName) {
		DatabaseSequence counter = mongo.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
				new Update().inc("seq", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
				DatabaseSequence.class);
		return !Objects.isNull(counter) ? counter.getSeq() : 1;
	}

	@Override
	public Employee getById(Integer id) {

		return repo.findById(id).get();
	}

	@Override
	public String deleteById(Integer id) {
		String dMsg = null;
		if (id != null) {
			repo.deleteById(id);
			dMsg = "ID :: " + id + " Employee is deleted";
		} else {
			dMsg = "Employee is not deleted";
		}
		return dMsg;
	}
}
