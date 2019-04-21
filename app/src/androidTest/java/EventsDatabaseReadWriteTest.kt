import android.content.Context
import androidx.test.runner.AndroidJUnit4
import com.prezyk.medcal.model.EventsDatabase
import org.junit.runner.RunWith
import androidx.test.core.app.*
import androidx.test.filters.SmallTest
import com.prezyk.medcal.model.dao.DrugDAO
import com.prezyk.medcal.model.dao.EventDAO
import com.prezyk.medcal.model.dao.TimeRangeDAO
import com.prezyk.medcal.model.model.Drug
import com.prezyk.medcal.model.model.Event
import com.prezyk.medcal.model.model.TimeRange
import com.prezyk.medcal.presenters.AddEventPresenter
import org.junit.*
import java.io.IOException
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class EventsDatabaseReadWriteTest {

    private lateinit var database: EventsDatabase
    private lateinit var timeRangeDAO: TimeRangeDAO
    private lateinit var eventDAO: EventDAO
    private lateinit var drugDAO: DrugDAO
    private lateinit var view: AddEventPresenter.View

    @Before
    fun initialize() {
        var context = ApplicationProvider.getApplicationContext<Context>()
        database = EventsDatabase.getEventsDatabase(context)!!

        timeRangeDAO = database.timeRangeDao()
        eventDAO = database.eventDao()
        drugDAO = database.drugDao()

        timeRangeDAO.deleteAll()
        eventDAO.deleteAll()
        drugDAO.deleteAll()


    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        timeRangeDAO.deleteAll()
        eventDAO.deleteAll()
        drugDAO.deleteAll()

//        database.close()
    }



    @Test
    @Throws(Exception::class)
    fun writeRead_1() {

        var trS1 = Calendar.getInstance()
        trS1.set(Calendar.MILLISECOND, 0)
        trS1.set(Calendar.SECOND, 0)
        trS1.set(Calendar.MINUTE, 0)

        var trE1 = trS1.clone() as Calendar

        trS1.set(Calendar.DAY_OF_MONTH, 22)
        trE1.set(Calendar.DAY_OF_MONTH, 24)



        var timeRange1 = TimeRange(null, trS1.timeInMillis, trE1.timeInMillis)

        var trID = timeRangeDAO.insert(timeRange1)

        timeRange1.eventID = trID

        var timeRange2 = timeRangeDAO.findByID(trID)

        Assert.assertEquals(timeRange1, timeRange2)


    }

    @Test
    @Throws(Exception::class)
    fun writeRead_2() {

        var trS1 = Calendar.getInstance()
        trS1.set(Calendar.MILLISECOND, 0)
        trS1.set(Calendar.SECOND, 0)
        trS1.set(Calendar.MINUTE, 0)


        var trS2 = trS1.clone() as Calendar
        var trE2 = trS1.clone() as Calendar

        trS2.set(Calendar.DAY_OF_MONTH, 21)
        trE2.set(Calendar.MONTH, 5)
        trE2.set(Calendar.DAY_OF_MONTH, 26)

        var timeRange2 = TimeRange(null, trS2.timeInMillis, trE2.timeInMillis)

        var trID = timeRangeDAO.insert(timeRange2)


        var eventList = ArrayList<Event>()

        var eventTime = trS2.clone() as Calendar
        while(eventTime.timeInMillis < trE2.timeInMillis) {
            eventTime.set(Calendar.HOUR_OF_DAY, 8)
            eventList.add(Event(eventTime.timeInMillis, trID))
            eventTime.set(Calendar.HOUR_OF_DAY, 16)
            eventList.add(Event(eventTime.timeInMillis, trID))
            eventTime.add(Calendar.WEEK_OF_YEAR, 1)
        }



        for(event in eventList) {
            eventDAO.insert(event)
        }

        var readEventList: List<Event> = eventDAO.findAllByTimeRangeID(trID)

        readEventList.sortedWith(compareBy { it.time })

        Assert.assertEquals(eventList, readEventList)



    }

    @Test
    @Throws(Exception::class)
    fun writeRead_3() {
        var trS3 = Calendar.getInstance()
        trS3.set(Calendar.MILLISECOND, 0)
        trS3.set(Calendar.SECOND, 0)
        trS3.set(Calendar.MINUTE, 0)

        var trE3 = trS3.clone() as Calendar

        trS3.set(Calendar.DAY_OF_MONTH, 22)
        trE3.set(Calendar.DAY_OF_MONTH, 24)

        var timeRange3 = TimeRange(null, trS3.timeInMillis, trE3.timeInMillis)

        var trID = timeRangeDAO.insert(timeRange3)

        var drugList = ArrayList<Drug>()

        drugList.add(Drug("DRUG1", trID))
        drugList.add(Drug("DRUG2", trID))
        drugList.add(Drug("DRUG3", trID))
        drugList.add(Drug("DRUG4", trID))

        for(d in drugList) {
            drugDAO.insert(d)
        }

        var readDrugList = drugDAO.findAllByTimeRangeID(trID)

        Assert.assertEquals(drugList, readDrugList)


    }


}