package uz.hayot.camunda.tasks.service;

import uz.hayot.camunda.tasks.model.process.Process;

import java.util.List;

public interface ProcessService {

    List<Process> getAll();

    Process add(Process process);

    Process update(Process process);

    void delete(String id);


}
