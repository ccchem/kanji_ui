#include <gtk/gtk.h>


void set_css(GtkWidget *window)
{
    GdkDisplay *display = gtk_widget_get_display(window);
    GtkCssProvider *provider = gtk_css_provider_new();
    gtk_css_provider_load_from_data(provider, ".kanji_font { font-size: 32pt; }", -1);
    gtk_style_context_add_provider_for_display(display, GTK_STYLE_PROVIDER(provider), 
        GTK_STYLE_PROVIDER_PRIORITY_APPLICATION);
}


void static radical_selected(GSimpleAction *action, GVariant *parameter, gpointer data)
{
    const char* radical = g_action_get_name(G_ACTION(action));
    g_print("Selected: %s \n", radical);
}


GtkWidget * create_rad_button(GtkApplication* app, const char* data)
{
    // Split data string into an array of radicals
    auto labels = g_strsplit(data, " ", 10);
    int len = g_strv_length(labels);

    // Create popup menu
    auto menu = g_menu_new();

    // Add radicals to the menu
    for(int i = 0; i < len; i++)
    {
        char* label = labels[i];

        // Add menu item
        auto app_action_str = g_strdup_printf("app.%s", label);
        g_menu_append(menu, label, app_action_str);
        g_free(app_action_str);

        // Create action
        auto action = g_simple_action_new(label, NULL);

        // Add the action to the action map 
        g_action_map_add_action(G_ACTION_MAP(app), G_ACTION(action));
        g_signal_connect(action, "activate", G_CALLBACK(radical_selected), NULL);
    }

    // Create menu button
    GtkWidget *button = gtk_menu_button_new();
    gtk_menu_button_set_label(GTK_MENU_BUTTON(button), labels[0]);
    // Add popup to the button (from model)
    gtk_menu_button_set_menu_model(GTK_MENU_BUTTON(button), G_MENU_MODEL(menu));

    // Add CSS classes to the button and popup menu items
    gtk_widget_add_css_class(button, "kanji_font");
    gtk_widget_add_css_class(GTK_WIDGET(gtk_menu_button_get_popover(GTK_MENU_BUTTON(button))), "kanji_font");

    // Delete resources
    g_strfreev(labels);

    return button;
}
