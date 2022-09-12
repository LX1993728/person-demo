package com.example.demo.service.impl;

import com.example.demo.entities.EmailEntity;
import com.example.demo.entities.PersonEntity;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import com.example.demo.tasks.AddPersonNotifyTask;
import com.example.demo.tasks.BaseTask;
import com.example.demo.utils.ThreadPoolManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonRepository personRepository;

    @Override
    public List<PersonEntity> getPersonList(){
        return personRepository.findAll();
    }


    @Transactional(rollbackOn = Exception.class)
    @Override
    public void savePerson(PersonEntity person){
        Assert.notNull(person, "the person saved cannot be null");
        if (person.getId() == null){
            personRepository.save(person);
            EmailEntity email = new EmailEntity(null, person.getEmail(), "add person", String.format("hello, %s", person.getName()), null);
            BaseTask<EmailEntity> task = new AddPersonNotifyTask(email);
            ThreadPoolManager.getInstance().execute(task);
        }
        personRepository.save(person);
    }

    @Override
    public PersonEntity findPersonById(Long id) {
        Optional<PersonEntity> personOption = personRepository.findById(id);
        return personOption.orElse(null);
    }


}
