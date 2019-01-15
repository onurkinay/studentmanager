import com.github.fluidsonic.fluid.json.*
import java.io.File
import java.io.StringWriter
class Functions{
    val fileName = "database.json"
    var file = File(fileName)
    fun getStudents(): MutableList<Student>{

        val isNewDatabase: Boolean = file.createNewFile()

        if(isNewDatabase){
         //   println("Database has been created")
        }else{
           // println("Database has already created")
        }
        var output = ""

        file.forEachLine { output += it }
        val parser = JSONCodingParser.builder()
            .decodingWith(EventCoded)
            .build()

        val json = parser.parseValueOfType<MutableList<Student>>(output)
        return json
    }

    fun saveDatabase(JSON: String): Boolean{
        file.writeText(JSON)

        return false
    }
}

data class Student (
        var Name: String?,
        var Surname: String?,
        var Number: String?,
        var Class: String?
    )

object EventCoded : AbstractJSONDecoderCodec<Student, JSONCodingContext>() {

    override fun decode(valueType: JSONCodingType<in Student>, decoder: JSONDecoder<JSONCodingContext>): Student {
        var Name: String? = ""
        var Surname: String? = ""
        var Number: String? = ""
        var Class: String? = ""
        //var Notes: String? = null


        decoder.readFromMapByElementValue { key ->
            when (key) {
                "Name" -> Name = readString()
                "Surname" -> Surname = readString()
                "Number" -> Number = readString()
                "Class" -> Class = readString()
                //  "Notes" -> Notes = readString()
                else -> skipValue()
            }
        }

        return Student(
            Name = Name.toString(),
            Surname = Surname.toString(),
            Number = Number.toString(),
            Class = Class.toString()
            // Notes = Notes.toString()
        )
    }
}

object EventCodec : AbstractJSONEncoderCodec<Student, JSONCodingContext>() {

    override fun encode(value: Student, encoder: JSONEncoder<JSONCodingContext>) {
        encoder.writeIntoMap {
            writeMapElement("Name", string = value.Name)
            writeMapElement("Surname", string = value.Surname)
            writeMapElement("Number", string = value.Number)
            writeMapElement("Class", string = value.Class)
        }
    }
}
fun encode(input: List<Student>): String {
    val writer = StringWriter()

    return JSONEncoder.builder()
        .codecs(EventCodec)
        .destination(writer)
        .build()
        .use { encoder ->
            encoder.writeListStart()
            for (event in input) {
                encoder.writeValue(event)
            }
            encoder.writeListEnd()

            return@use writer.toString()
        }
}
