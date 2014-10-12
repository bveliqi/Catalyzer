package com.hackzurich.catalyzer.resources;

import com.hackzurich.catalyzer.api.Project;
import com.hackzurich.catalyzer.jdbi.EventDao;
import com.hackzurich.catalyzer.jdbi.ProjectDao;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.sql.Timestamp;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by behar on 11.10.14.
 */
public class ProjectResourceTest {

    private static final ProjectDao dao = mock(ProjectDao.class);
    private Project project;

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new ProjectResource(dao, mock(EventDao.class)))
            .build();

    @Before
    public void setUp() throws Exception {
        project = new Project();
        project.setAuthorId(1);
        project.setCategory("Enviroment");
        project.setName("Clean the streets of Zürich");
        project.setMotivation("For Fun");
        project.setPhotoUrl("a/b/c.jpg");
        project.setStatus("OK");
        project.setStartDate(new Timestamp(DateTime.now().getMillis()));

        when(dao.getById(eq(1L))).thenReturn(project);
    }

    @Test
    public void testGetPersonById() throws Exception {
        assertThat(resources.client().resource("/project/1").get(Project.class))
                .isEqualTo(project);
        verify(dao).getById(eq(1L));
    }
}
