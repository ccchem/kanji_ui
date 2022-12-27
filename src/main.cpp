#include <gtk/gtk.h>


// #include "utils.h"
void set_css(GtkWidget *window);
GtkWidget * create_rad_button(GtkApplication* app, const char* labels);


static GtkWidget * create_grid(GtkApplication* app);


static void activate(GtkApplication* app, gpointer user_data)
{
    g_object_set(gtk_settings_get_default(), "gtk-application-prefer-dark-theme", TRUE, NULL);

    GtkWidget *window = gtk_application_window_new(app);
    set_css(window);
    gtk_window_set_title(GTK_WINDOW(window), "Kanji Lookup");
    gtk_window_set_default_size(GTK_WINDOW(window), 1000, 600);

    GtkWidget *grid = create_grid(app);
    gtk_window_set_child(GTK_WINDOW(window), grid);

    gtk_widget_show(window);
}


static GtkWidget * create_grid(GtkApplication* app)
{
    GtkWidget *grid = gtk_grid_new();

    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "日 月 門"), 0, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "目 貝 頁 耳"), 1, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "皿 亜"), 2, 0, 1, 1);


    return grid;
}


int main(int argc, char **argv)
{
    GtkApplication *app = gtk_application_new("ek.kanji.lookup", G_APPLICATION_FLAGS_NONE);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    int status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);

    return status;
}
