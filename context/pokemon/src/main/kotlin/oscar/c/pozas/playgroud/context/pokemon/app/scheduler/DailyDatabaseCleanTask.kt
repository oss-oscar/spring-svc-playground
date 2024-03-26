package oscar.c.pozas.playgroud.context.pokemon.app.scheduler

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.scheduling.annotation.Scheduled
import oscar.c.pozas.playground.spring.annotation.ScheduleTask

@ScheduleTask
class DailyDatabaseCleanTask {

    @Scheduled(cron = "0 0 8 * * *", zone = "Europe/Madrid")
    @SchedulerLock(name = "cleanDatabaseDailyTask", lockAtMostFor = "PT20H", lockAtLeastFor = "PT20H")
    fun cleanDatabaseDailyAt8AM() {
        // TODO()
    }
}
