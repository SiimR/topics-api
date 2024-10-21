package com.example;


import lombok.RequiredArgsConstructor;
import org.jooq.Field;
import org.jooq.RecordContext;
import org.jooq.RecordListener;
import org.jooq.impl.DSL;

import java.time.Clock;
import java.time.Instant;
import java.util.UUID;


@RequiredArgsConstructor
public class TopicsRecordListener implements RecordListener {

  private static final Field<UUID> ID = DSL.field("id", UUID.class);
  private static final Field<String> CREATED_BY = DSL.field("created_by", String.class);
  private static final Field<Instant> CREATED_AT = DSL.field("created_at", Instant.class);
  private final Clock clock;

  @Override
  public void insertStart(RecordContext ctx) {
    ctx.record().set(ID, UUID.randomUUID());
    updateAuditable(ctx, true);
  }

  @Override
  public void updateStart(RecordContext ctx) {
    updateAuditable(ctx, false);
  }

  private void updateAuditable(RecordContext ctx, boolean isCreating) {
    var dbRecord = ctx.record();
    var now = clock.instant();
    if (isCreating) {
      dbRecord.setValue(CREATED_BY, "user");
      dbRecord.setValue(CREATED_AT, now);
    }
  }
}
