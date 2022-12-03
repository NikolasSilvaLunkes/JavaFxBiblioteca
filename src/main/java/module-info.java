module project.nikolas.javaFx.javaFxProj1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires javafx.base;

    opens project.nikolas.javaFx.javaFxProj1 to javafx.fxml;
    exports project.nikolas.javaFx.javaFxProj1;
    exports project.nikolas.javaFx.javaFxProj1.Classes;

    exports project.nikolas.javaFx.javaFxProj1.DaoClasses;
}
