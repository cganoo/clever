package client;

import api.CleverAPI;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import model.districts.GetDistrictsResponse;
import model.sections.GetSectionsResponse;
import model.sections.SectionWithUri;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Modelling the object used to extract info from Clever API as a {@link com.google.common.util.concurrent.Service}
 * This is more so for easier extension and the scheduler is not of any real use currently
 *
 * Created by cganoo on 28/01/2016.
 */
public class CleverClient extends AbstractScheduledService {

    private static final Logger log = LoggerFactory.getLogger(CleverClient.class);

    private final CleverAPI api;

    @Inject
    public CleverClient(@Named("CleverAPI") CleverAPI api) {
        this.api = api;
    }

    @Override
    protected void runOneIteration() throws Exception {
        log.info("Average number of students per section across all districts is: {}", getAvgStudentsPerSection());
    }

    @Override
    protected Scheduler scheduler() {
        return Scheduler.newFixedRateSchedule(0, 60, TimeUnit.SECONDS);
    }

    /**
     * Queries the Clever API endpoint for districts and students per section in these districts
     * @return average number of students per section across all districts
     * @throws IOException
     */
    private Long getAvgStudentsPerSection() throws IOException {
        final List<String> districtIds = getDistrictIds();
        if(districtIds == null || districtIds.isEmpty()) {
            log.warn("No districts found");
            return 0L;
        }
        final List<Pair<Long, Long>> tuples = Lists.newArrayList();
        for(final String districtId : districtIds) {
            // For each district calculate # of sections and # of students
            Pair<Long, Long> result = getSectionStudentTuplesInDistrict(districtId);
            if(result == null) {
                // skip over this null result
                continue;
            }
            final long sections = result.getLeft();
            final long students = result.getRight();
            log.info("Found {} sections and {} students for district {}", sections, students, districtId);
            tuples.add(result);
        }
        // Count total students and sections separately
        final Pair<Long, Long> result = tuples.stream().reduce(Pair.of(0L, 0L), (acc, p) -> Pair.of(acc.getLeft() + p.getLeft(), acc.getRight() + p.getRight()));
        log.info("Found a total of {} sections and {} students across all districts", result.getLeft(), result.getRight());
        return Long.valueOf(result.getRight() / result.getLeft());
    }

    /**
     * Get a list of all district ids
     * @return {@link List} of district ids
     * @throws IOException
     */
    private List<String> getDistrictIds() throws IOException {
        final Call<GetDistrictsResponse> callDistricts = api.getDistricts();
        final Response<GetDistrictsResponse> response = callDistricts.execute();
        // Collect the ids of all districts into a list
        return response.body().getData().stream().map(x -> x.getDistrict().getId()).collect(Collectors.toList());
    }

    /**
     * Get a count of sections and students within the specified district
     * @param districtId id of the district we are interested in
     * @return {@link Pair} of total sections and students in the specified district
     * @throws IOException
     */
    private Pair<Long, Long> getSectionStudentTuplesInDistrict(final String districtId) throws IOException {
        try {
            final Call<GetSectionsResponse> callSectionsForDistrict = api.getSectionsForDistrict(districtId);
            final Response<GetSectionsResponse> response = callSectionsForDistrict.execute();
            final List<SectionWithUri> sectionsList = response.body().getData();
            long totalSections = sectionsList.size();
            // Extract students ids from each section into a list
            final List<Integer> sectionalStudentCounts = sectionsList.stream().map(x -> x.getSection().getStudentIds().size()).collect(Collectors.toList());
            // Count the total students
            long totalStudents = sectionalStudentCounts.stream().reduce(0, (acc, item) -> acc + item);
            return Pair.of(totalSections, totalStudents);
        } catch (RuntimeException ex) {
            log.error("Encountered exception [{}] when querying sections for district {}", ex, districtId);
            throw ex;
        }
    }
}
