package uz.hayot.camunda.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.hayot.camunda.tasks.exception.DataAlreadyExists;
import uz.hayot.camunda.tasks.model.process.ProcessType;
import uz.hayot.camunda.tasks.repository.ProcessTypeRepository;
import uz.hayot.camunda.tasks.service.ProcessTypeService;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessTypeServiceImpl implements ProcessTypeService {

    private final ProcessTypeRepository processTypeRepository;

    @Override
    public List<ProcessType> getAll() {
        return StreamSupport.stream(processTypeRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public ProcessType add(ProcessType process) {
        if(processTypeRepository.findById(process.getId()).isPresent()) throw new DataAlreadyExists("Данные Уже Присутствуют");
        return processTypeRepository.save(process);    }

    @Override
    public ProcessType update(ProcessType process) {
        return processTypeRepository.save(process);
    }

    @Override
    public void delete(Long id) {
        processTypeRepository.deleteById(id);
    }
}
