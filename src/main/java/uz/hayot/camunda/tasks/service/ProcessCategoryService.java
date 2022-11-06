package uz.hayot.camunda.tasks.service;

import uz.hayot.camunda.tasks.model.process.ProcessCategory;

import java.util.List;

public interface ProcessCategoryService {

    List<ProcessCategory> getAll();

    ProcessCategory add(ProcessCategory process);

    ProcessCategory update(ProcessCategory process);

    void delete(Long id);
}
