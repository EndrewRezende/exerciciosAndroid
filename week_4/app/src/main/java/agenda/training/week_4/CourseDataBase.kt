package agenda.training.week_4

import com.example.week_4.Student
import kotlin.random.Random

object CourseDataBase {
    private const val longNameCourse = "Curso com um nome muito longo para aparecer em uma unica linha por inteiro"
    private const val computerScienceCourse = "Ciencia da Computacao"
    private const val informationSystemsCourse = "Sistema da informacao"
    const val morningShift = "Manhã"
    const val afternoonShift = "Tarde"
    const val nightShift = "Noite"
    val courses = listOf(longNameCourse, computerScienceCourse, informationSystemsCourse)

    val studentsPreRegistered = mutableListOf(
        Student(Random.nextInt(), "Luis Felipe de Almeida da Silva Soares Souza Ferreira Paulista", "Avenida Paulista 1157", "+55 11 4002-8922", "luis@madeinweb.com.br", longNameCourse, morningShift),
        Student(Random.nextInt(), "Murilo", "Avenida Paulista 1164", "+55 21 4002-8936", "murilo@madeinweb.app", informationSystemsCourse, afternoonShift)
    )
}