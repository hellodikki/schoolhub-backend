package ma.emsi.schoolhubbackend.services;

import ma.emsi.schoolhubbackend.entities.Group;
import ma.emsi.schoolhubbackend.repositories.GroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GroupServiceTests {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupService groupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveGroup() {
        Group group = new Group();
        group.setName("Physics 101");
        when(groupRepository.save(any(Group.class))).thenReturn(group);
        Group savedGroup = groupService.addGroup(new Group());
        assertNotNull(savedGroup);
        verify(groupRepository).save(any(Group.class));
    }

    @Test
    void testUpdateGroup() {
        Group originalGroup = new Group();
        originalGroup.setId(1L);
        originalGroup.setName("Math 101");

        Group updatedDetails = new Group();
        updatedDetails.setName("Advanced Math 101");

        when(groupRepository.findById(1L)).thenReturn(Optional.of(originalGroup));
        when(groupRepository.save(any(Group.class))).thenReturn(originalGroup);

        Group updatedGroup = groupService.updateGroup(1L, updatedDetails);

        assertNotNull(updatedGroup);
        assertEquals("Advanced Math 101", updatedGroup.getName());
        verify(groupRepository).findById(1L);
        verify(groupRepository).save(originalGroup);
    }

    @Test
    void testDeleteGroup() {
        doNothing().when(groupRepository).deleteById(1L);
        groupService.deleteGroup(1L);
        verify(groupRepository).deleteById(1L);
    }
}
