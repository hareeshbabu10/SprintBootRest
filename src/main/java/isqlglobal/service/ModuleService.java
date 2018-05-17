package isqlglobal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isqlglobal.dao.ModuleDAO;
import isqlglobal.entity.Module;
@Service
public class ModuleService {
@Autowired
  ModuleDAO moduleDAO;

public List<Module> getModules(){
	 Iterable<Module> i=moduleDAO.findAll();
	 List<Module> l=new ArrayList<Module>();
	 
	 for(Module x:i){
		 l.add(x);
	 }
	 return l;
}

public Module getModules(String module){
	 Iterable<Module> i=moduleDAO.findAll();
	
	 
	 for(Module x:i){
		 if(x.getModulecode().contentEquals(module))
			 return x;
	 }
	 return null;
}
}
