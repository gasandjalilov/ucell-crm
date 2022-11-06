package uz.hayot.camunda.tasks.service;

import uz.hayot.camunda.tasks.model.process.ProcessType;

import java.util.List;

public interface ProcessTypeService {

    List<ProcessType> getAll();

    ProcessType add(ProcessType process);

    ProcessType update(ProcessType process);

    void delete(Long id);
}
