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

    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "口 兄 言 石"), 0, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "日 月 門"), 1, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "目 貝 見 頁 耳"), 2, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "皿 冊 而 亜"), 3, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "田 車 里 重"), 4, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "用 再 毋 毎"), 5, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "冂 几 囗 匚"), 6, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "向 同 咼 周"), 7, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "岡 両 禸"), 8, 0, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "巾 雨"), 9, 0, 1, 1);

    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "木 禾 米"), 0, 1, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "大 矢"), 1, 1, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "夂 攵 女 又 文"), 2, 1, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "父 交"), 3, 1, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "厶 幺 糸 亥"), 4, 1, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "匕 比"), 5, 1, 1, 1);

    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "亻 彳 忄 礻"), 0, 2, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "中 虫"), 1, 2, 1, 1);
    gtk_grid_attach(GTK_GRID(grid), create_rad_button(app, "立 啇"), 2, 2, 1, 1);

    


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
