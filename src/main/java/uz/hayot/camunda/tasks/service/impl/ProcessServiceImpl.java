package uz.hayot.camunda.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.exception.DataAlreadyExists;
import uz.hayot.camunda.tasks.model.process.Process;
import uz.hayot.camunda.tasks.repository.ProcessRepository;
import uz.hayot.camunda.tasks.service.ProcessService;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessServiceImpl implements ProcessService {

    private final ProcessRepository processRepository;


    @Override
    public List<Process> getAll() {
        return StreamSupport.stream(processRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public Process add(Process process) {
        if(processRepository.findById(process.getProcessId()).isPresent()) throw new DataAlreadyExists("Данные Уже Присутствуют");
        return processRepository.save(process);
    }

    @Override
    public Process update(Process process) {
        return processRepository.save(process);
    }

    @Override
    public void delete(String id) {
        processRepository.deleteById(id);
    }
}
