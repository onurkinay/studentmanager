import com.github.fluidsonic.fluid.json.*
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer


class Menu {
    fun AddStudent(Name: String?, Surname: String?, Number: String?, Classroom: String?){

    /*  )*/

        var students: MutableList<Student> = Functions().getStudents()
        students.add( Student(Name, Surname,Number,Classroom) )

        val serializer = JSONCodingSerializer.builder()
            .encodingWith(EventCodec)
            .build()

        val newStudent = encode(students)
        Functions().saveDatabase(newStudent)

    }

    fun DeleteStudent(Number: String?){
        var students: MutableList<Student> = Functions().getStudents()
        students.remove( students.filter { it.Number == Number }.first() )

        Functions().saveDatabase( encode(students) )



    }
    fun ChangeStudent(Name: String?, Surname: String?, Number: String?, Classroom: String?){
        var students: MutableList<Student> = Functions().getStudents()
        var student = students.filter { it.Number == Number }.first()
        var ID = students.indexOf( student )

        student.Name = Name
        student.Surname = Surname
        student.Class = Classroom
        student.Number = Number

        students[ID] = student

        Functions().saveDatabase( encode(students) )

    }
    fun GetStudent(Number: String?): Student{
        return Functions().getStudents().filter { it.Number == Number }.first()

    }
}