package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICommentToDtoMapper {

    ICommentToDtoMapper INSTANCE = Mappers.getMapper(ICommentToDtoMapper.class);

    CommentDto commentToCommentDto(Comment comment);

    Comment commentDtoToCommentEntity(CommentDto commentDto);


}
