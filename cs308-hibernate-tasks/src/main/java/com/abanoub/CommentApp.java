package com.abanoub;

import java.util.ArrayList;
import java.util.List;

import com.abanoub.criteria.FilterQuery;
import com.abanoub.criteria.Operator;
import com.abanoub.dao.DBComments;
import com.abanoub.dao.DBConfig;
import com.abanoub.models.Comment;

public class CommentApp {
    public static void main(String[] args) {
        DBComments dbComments = new DBComments();

        Comment newComment = new Comment();
        newComment.setContent("Great Blog!");
        newComment.setIsApproved("true");
        newComment.setBlogId(1);

        Comment newComment2 = new Comment();
        newComment2.setContent("Find something else to do");
        newComment2.setIsApproved("false");
        newComment2.setBlogId(1);

        System.out.println("INSERTED A NEW Comments WITH ID: " +
                dbComments.insert(newComment));
        System.out.println("INSERTED A NEW Comments WITH ID: " +
                dbComments.insert(newComment2));

        DBComments dbComment = new DBComments();
        List<FilterQuery> filterQueries = new ArrayList<>();

        filterQueries.add(new FilterQuery("commentId", "0", Operator.GT));
        for (Comment e : dbComment.getByFilter(filterQueries)) {
            System.out.println(e);
        }

        filterQueries.add(new FilterQuery("isApproved", "true", Operator.EQ));

        DBConfig.shutdown();
    }
}
