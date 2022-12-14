package uz.ucell.tasks.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.ucell.tasks.exception.DataAlreadyExists;
import uz.ucell.tasks.model.process.ProcessCategory;
import uz.ucell.tasks.repository.ProcessCategoryRepository;
import uz.ucell.tasks.service.ProcessCategoryService;

import java.util.List;
import java.util.stream.StreamSupport;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProcessCategoryServiceImpl implements ProcessCategoryService {

    private final ProcessCategoryRepository processCategoryRepository;


    @Override
    public List<ProcessCategory> getAll() {
        return StreamSupport.stream(processCategoryRepository.findAll().spliterator(), false).toList();
    }

    @Override
    public ProcessCategory add(ProcessCategory process) {
        if(processCategoryRepository.findById(process.getId()).isPresent()) throw new DataAlreadyExists("Данные Уже Присутствуют");
        return processCategoryRepository.save(process);    }

    @Override
    public ProcessCategory update(ProcessCategory process) {
        return processCategoryRepository.save(process);
    }

    @Override
    public void delete(Long id) {
        processCategoryRepository.deleteById(id);
    }
}
