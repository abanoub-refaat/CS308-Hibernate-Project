package com.abanoub;

import com.abanoub.dao.DBBlog;
import com.abanoub.dao.DBConfig;
import com.abanoub.models.Blog;

public class BlogApp {

    public static void main(String[] args) {
        DBBlog dbBlog = new DBBlog();

        // adding first Blog:
        Blog newBlog = new Blog();
        newBlog.setTitle("Better Lesson");
        newBlog.setPubDate("13-Sep-2023");

        Blog newBlog2 = new Blog();
        newBlog2.setTitle("Edutopia");
        newBlog2.setPubDate("09-Oct-2005");

        System.out.println("INSERTED A NEW Blog WITH ID: " +
                dbBlog.insert(newBlog));
        System.out.println("INSERTED A NEW Blog WITH ID: " +
                dbBlog.insert(newBlog2));
        DBConfig.shutdown();
    }

}
