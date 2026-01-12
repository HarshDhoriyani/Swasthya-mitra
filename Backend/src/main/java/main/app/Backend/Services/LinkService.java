package main.app.Backend.Services;

import main.app.Backend.Entities.LinkEntity;
import main.app.Backend.Repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    LinkRepository linkRepository;

    public List<LinkEntity> findLinks(){
        return linkRepository.findAll();
    }
    public LinkEntity saveLink(LinkEntity link){
        return linkRepository.save(link);
    }
    public void deleteLink(Long id){
        linkRepository.deleteById(id);
    }

}
