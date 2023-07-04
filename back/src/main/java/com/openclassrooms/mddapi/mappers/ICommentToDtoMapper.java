package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * The interface Comment to dto mapper.
 */
@Mapper
public interface ICommentToDtoMapper {

    /**
     * The constant INSTANCE.
     */
    ICommentToDtoMapper INSTANCE = Mappers.getMapper(ICommentToDtoMapper.class);

    /**
     * Comment to comment dto comment dto.
     *
     * @param comment the comment
     * @return the comment dto
     */
    CommentDto commentToCommentDto(Comment comment);

    /**
     * Comment dto to comment entity comment.
     *
     * @param commentDto the comment dto
     * @return the comment
     */
    Comment commentDtoToCommentEntity(CommentDto commentDto);


}
