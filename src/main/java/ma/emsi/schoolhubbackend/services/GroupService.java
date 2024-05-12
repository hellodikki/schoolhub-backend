package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Group;
import ma.emsi.schoolhubbackend.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long groupId, Group groupDetails) {
        return groupRepository.findById(groupId)
                .map(group -> {
                    group.setName(groupDetails.getName());
                    group.setYear(groupDetails.getYear());
                    group.setSector(groupDetails.getSector());
                    return groupRepository.save(group);
                }).orElseThrow(() -> new RuntimeException("Group not found"));
    }

    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }
}

