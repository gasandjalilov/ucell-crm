package uz.ucell.tasks.service;

import uz.ucell.tasks.model.process.ProcessCategory;

import java.util.List;

public interface ProcessCategoryService {

    List<ProcessCategory> getAll();

    ProcessCategory add(ProcessCategory process);

    ProcessCategory update(ProcessCategory process);

    void delete(Long id);
}
