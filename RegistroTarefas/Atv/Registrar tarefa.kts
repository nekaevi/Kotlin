// Task.kt
data class Task(
    val title: String,
    val description: String,
    var isCompleted: Boolean = false
)

// TaskAdapter.kt
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(val tasks: MutableList<Task>, private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.descriptionTextView.text = task.description
        holder.completedCheckBox.isChecked = task.isCompleted
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.task_title)
        val descriptionTextView: TextView = view.findViewById(R.id.task_description)
        val completedCheckBox: CheckBox = view.findViewById(R.id.task_completed_checkbox)
        
        init {
            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                tasks[adapterPosition].isCompleted = isChecked
                notifyItemChanged(adapterPosition)
            }
        }
    }
}

// TaskActivity.kt
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter
    private lateinit var tasks: MutableList<Task>
    private lateinit var inputTitleEditText: EditText
    private lateinit var inputDescriptionEditText: EditText
    private lateinit var addButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        setupViews()
        initializeTasks()

        // Adicionar novas tarefas quando o botão é clicado
        addButton.setOnClickListener {
            val title = inputTitleEditText.text.toString().trim()
            val description = inputDescriptionEditText.text.toString().trim()
            
            if (!title.isEmpty()) {
                val newTask = Task(title, description)
                tasks.add(newTask)
                adapter.notifyItemInserted(tasks.size - 1)
                
                // Limpar campos após adicionar
                inputTitleEditText.text.clear()
                inputDescriptionEditText.text.clear()
            }
        }
    }

    private fun setupViews() {
        recyclerView = findViewById(R.id.tasks_recycler_view)
        inputTitleEditText = findViewById(R.id.input_title_edit_text)
        inputDescriptionEditText = findViewById(R.id.input_description_edit_text)
        addButton = findViewById(R.id.add_button)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(tasks, this)
        recyclerView.adapter = adapter
    }

    private fun initializeTasks() {
        tasks = mutableListOf(
            Task("Comprar leite", "Ir à loja comprar leite"),
            Task("Fazer exercícios", "Realizar exercícios físicos"),
            Task("Ler um livro", "Concluir o livro que está lendo")
        )
    }
}

// activity_task.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/android/app"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".TaskActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/tasks_recycler_view"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scrollbars="vertical"
        app:layout_constraintBottom_toTopOf="@+id/add_button_container"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/add_button_container"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent">

        <Button
            android:id="@+id/add_button"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Adicionar Tarefa"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />

    </androidx.constraintlayout.widget.ConstraintLayout>

    <EditText
        android:id="@+id/input_title_edit_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Título da Tarefa"
        android:inputType="textPersonName"
        app:layout_constraintEnd_toStartOf="@+id/input_description_edit_text"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/input_description_edit_text"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp"
        android:hint="Descrição da Tarefa"
        android:inputType="textMultiLine"
        app:layout_constraintEnd_toStartOf="@+id/input_description_edit_text"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/input_title_edit_text" />

</androidx.constraintlayout.widget.ConstraintLayout>

// task_item.xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/task_title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="18sp" />

    <TextView
        android:id="@+id/task_description"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="14sp" />

    <CheckBox
        android:id="@+id/task_completed_checkbox"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>

