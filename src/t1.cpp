#include <gtk/gtk.h>


static GtkWidget * create_grid(GtkApplication* app);


static void activate (GtkApplication* app, gpointer user_data)
{
    g_object_set(gtk_settings_get_default(), "gtk-application-prefer-dark-theme", TRUE, NULL);

    GtkWidget *window = gtk_application_window_new(app);
    gtk_window_set_title(GTK_WINDOW(window), "Window");
    gtk_window_set_default_size(GTK_WINDOW(window), 1000, 600);
    
    GtkWidget *grid = create_grid(app);
    gtk_window_set_child(GTK_WINDOW(window), grid);

    gtk_widget_show (window);
}


static void radical_selected(GSimpleAction *action, GVariant *parameter, gpointer data)
{
    g_print("Selected: %s \n", data);
}


static GtkWidget * create_grid(GtkApplication* app)
{
    GtkWidget *grid = gtk_grid_new();

    static const char* labels[] = {"日", "月", "門"};

    auto menu = g_menu_new();
    
    int count = 0;
    for(auto label: labels)
    {
        count++;
        g_menu_append(menu, label, g_strdup_printf("app.rad_%i", count));

        auto action = g_simple_action_new(g_strdup_printf("rad_%i", count), NULL);
        g_action_map_add_action (G_ACTION_MAP(app), G_ACTION(action));
        g_signal_connect(action, "activate", G_CALLBACK(radical_selected), const_cast<char*>(label));
    }

    GtkWidget *d1 = gtk_menu_button_new();
    gtk_menu_button_set_label(GTK_MENU_BUTTON(d1), labels[0]);
    gtk_menu_button_set_menu_model(GTK_MENU_BUTTON(d1), G_MENU_MODEL(menu));

    gtk_grid_attach(GTK_GRID(grid), d1, 0, 0, 1, 1);
    return grid;
}


int main(int argc, char **argv)
{
    GtkApplication *app = gtk_application_new("org.gtk.example", G_APPLICATION_FLAGS_NONE);
    g_signal_connect(app, "activate", G_CALLBACK(activate), NULL);
    int status = g_application_run(G_APPLICATION(app), argc, argv);
    g_object_unref(app);

    return status;
}
